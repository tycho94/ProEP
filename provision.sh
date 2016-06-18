#!/usr/bin/env bash

main() {
    curl -sL https://deb.nodesource.com/setup_5.x | sudo -E bash -
    sudo apt-get install build-essential checkinstall libssl-dev nodejs -y
    mkdir -p /home/vagrant/.config
    chmod -R o+rwx /home/vagrant/.config
    # for some reason these binaries are not installed
    sudo npm install -g gulp gulp-typescript concurrently lite-server npm-check-updates
}

auto_cd() {
	local LINE='cd /home/vagrant/frontend'

	if ! grep -qF "${LINE}" '/home/vagrant/.bashrc'; then
		eval "echo '${LINE}' >> /home/vagrant/.bashrc"
	fi
}

main "${@}"
auto_cd "${@}"

