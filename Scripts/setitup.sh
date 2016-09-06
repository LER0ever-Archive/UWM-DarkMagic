#~/usr/bin/env bash
mkdir -p ~/LER0ever
cd ~/LER0ever
echo ‘Downloading MacVim DMG’
curl -LOk -o macvim.dmg https://github.com/macvim-dev/macvim/releases/download/snapshot-107/MacVim.dmg
hdiutil attach ~/LER0ever/macvim.dmg
echo ‘Downloading Telegram DMG’
curl -LOk -o telegram.dmg https://updates.tdesktop.com/tmac/tsetup.0.10.1.dmg
hdiutil attach ~/LER0ever/telegram.dmg
echo 'Fetching Config from LER0ever EverVim'
git clone https://github.com/LER0ever/EverVim
echo 'Setting up EverVim'
cd EverVim
cp .vim* ~/
chmod +x *.sh
./Boot-EverVim.sh
echo 'Ready to Go.'
