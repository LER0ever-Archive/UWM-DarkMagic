#~/usr/bin/env bash
mkdir -p ~/LER0ever
cd ~/LER0ever

# MacVim
echo ‘Downloading MacVim DMG’
curl -LOk -o macvim.dmg https://github.com/macvim-dev/macvim/releases/download/snapshot-107/MacVim.dmg
hdiutil attach ~/LER0ever/macvim.dmg

#Telegram
echo ‘Downloading Telegram DMG’
curl -LOk -o telegram.dmg https://updates.tdesktop.com/tmac/tsetup.0.10.1.dmg
hdiutil attach ~/LER0ever/telegram.dmg

#EverVim
echo 'Fetching Config from LER0ever EverVim'
git clone https://github.com/LER0ever/EverVim
echo 'Setting up EverVim\n\n'
ln -s ~/LER0ever/EverVim/.vimrc ~/.vimrc
ln -s ~/LER0ever/EverVim/.vimrc.before ~/.vimrc.before
ln -s ~/LER0ever/EverVim/.vimrc.local ~/.vimrc.local
ln -s ~/LER0ever/EverVim/.vimrc.bundles ~/.vimrc.bundles
echo 'Welcome to EverVim, a full featured vim configuration based on spf13-vim'
echo 'Booting EverVim ...'
echo 'Creating Directories ...'
mkdir -p ~/.vim/bundle/
cd ~/.vim/bundle/
echo 'Cloning Vundle Plugin Manager to bundle ...'
git clone https://github.com/VundleVim/Vundle.vim.git vundle
echo 'All done with EverVim, now run vim and execute ":BundleInstall"'
cd ../../LER0ever

# Repos
echo 'Fetching Common Repos ...'
git clone https://github.com/LER0ever/HydroDev.git
git clone https://github.com/LER0ever/Lumos.git
echo 'Ready to Go.'
