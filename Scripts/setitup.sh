#~/usr/bin/env bash
echo 'Preparing HomeBrew'
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
echo 'Downloading Telegram'
brew install caskroom/cask/brew-cask 2> /dev/null
brew cask install telegram
echo 'Downloading Vim'
brew tap macvim-dev/macvim
brew install --HEAD macvim-dev/macvim/macvim
echo 'Fetching Config from LER0ever EverVim'
git clone https://github.com/LER0ever/EverVim
echo 'Setting up EverVim'
cd EverVim
cp .vim* ~/
chmod +x *.sh
./BootEverVim.sh
echo 'Ready to Go.'
