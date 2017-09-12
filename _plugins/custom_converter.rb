require 'redcarpet'
require 'rouge'

module Jekyll
  class CustomConverter < Converter
    safe true
    priority :high

    # Surcouche au render de base de Redcarpet pour supporter le surlignage
    # de certaines lignes de code.
    #
    # Par exemple, dans un document Markdown :
    #
    #   ~~~ruby?1
    #   ...
    #   ~~~
    #
    # permet de colorer du code Ruby et de surligner la ligne 1. Il est possible
    # de surligner plusieurs lignes en les séparant par des virgules :
    #
    #   ~~~ruby?1,2,3
    #
    # Des intervalles peuvent même être spécifiés. L'exemple du dessus peut être
    # écrit :
    #
    #   ~~~ruby?1..3
    class CustomRender < Redcarpet::Render::HTML
      def block_code(code, lang, linenos = nil)
        lang, highlights = lang ? lang.split('?') : [nil, nil]
        lexer = Rouge::Lexer.find_fancy(lang, code) || Rouge::Lexer::PlainText

        if highlights
          highlights = highlights.split(',').map do |value|
            # Pour un interval (e.g. 1..5, lignes de 1 à 5)
            if value.include?('..')
              bounds = value.split('..')
              (bounds[0].to_i..bounds[1].to_i).to_a
            else
              value.to_i
            end
          end.flatten
        end

        highlights ||= []

        highlighted = CustomFormatter.new({
          highlights: highlights
        }).format(lexer.lex(code))

        %(<pre class="highlight">#{highlighted}</pre>)
      end

      def link(link, title, content)
        markup =  %(<a href="#{link}")
        markup << %( title="#{title}") if title
        markup << %(>)
        markup << %(<img src="/icons/download.svg"> ) if link.start_with?("https://drive.")
        markup << content
        markup << "</a>"
        markup
      end
    end

    class CustomFormatter < Rouge::Formatters::HTML
      def initialize(opts)
        super

        @highlights = opts[:highlights]
        @lineno     = 0
      end

      def stream(tokens, &b)
        token_lines(tokens) do |line|
          @lineno += 1

          if @highlights.include?(@lineno)
            yield %(<span class="hll">)
            line.each { |tok, val| yield span(tok, val) }
            yield %(</span>)
          else
            line.each { |tok, val| yield span(tok, val) }
          end

          yield "\n"
        end
      end
    end

    def matches(ext)
      ext =~ /^\.md$/i
    end

    def output_ext(ext)
      ".html"
    end

    def convert(content)
      options = {
        fenced_code_blocks: true,
        no_intra_emphasis: true,
        tables: true
      }

      @render ||= Redcarpet::Markdown.new(CustomRender, options)
      @render.render(content)
    end
  end
end
