SUMMARY = "Example recipe that inherits the useradd class"
DESCRIPTION = "Creates a user account named 'user' with its home directory"
LICENSE = "MIT"
PR = "r1"

S = "${WORKDIR}"

inherit useradd

USERADD_PACKAGES = "${PN}"

# Create a new user "user" with /home/user as home directory
# and /bin/sh as default shell
USERADD_PARAM:${PN} = "-u 1000 -d /home/user -r -s /bin/sh user"

do_install () {
    install -d -m 755 ${D}/home/user
    chown -R user ${D}/home/user
    chgrp -R user ${D}/home/user
}

FILES:${PN} = "/home/user"

