require 'io/console'
require 'net/ftp'

task :deploy do
  print "Utilisateur : "
  user = STDIN.gets.chomp

  print "Password : "
  pass = STDIN.noecho(&:gets).chomp

  puts "\nUpload des fichiers..."

  Net::FTP.open('ftp.alwaysdata.com') do |ftp|
    ftp.login(user, pass)

    ftp.chdir('www')

    Dir.glob('_site/**/*') do |file|
      next if File.directory?(file)

      net_name = file.sub('_site/', '')
      md_file  = file.sub('_site/', '')
                     .sub('.html', '.md')

      begin
        if File.stat(md_file).ctime > ftp.mtime(net_name)
          ftp.put(file, net_name)
          puts "* Mise à jour du fichier #{net_name}"
        end
      rescue Net::FTPPermError
        folders = net_name.split('/')
        folders.pop # Pour retirer le nom du fichier de la liste

        folders.each do |folder|
          begin ftp.mkdir(folder) rescue nil end
          ftp.chdir(folder)
        end

        ftp.chdir('/www')
        ftp.put(file, net_name)

        puts "+ Ajout du fichier #{net_name}"
      end
    end
  end

  puts "Finish ! ✔ \\o/"
end
