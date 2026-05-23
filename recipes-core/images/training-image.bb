require recipes-core/images/core-image-minimal.bb                               

DESCRIPTION = "Custom image for the training"
LICENSE = "MIT"

SKELSERVICE = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'service-systemd', 'service-sysvinit', d)}"
CORE_IMAGE_EXTRA_INSTALL = "bbexample thisis libanswer newuser ${SKELSERVICE} hello-mod"

inherit extrausers                                                              

PASSWD = "\$5\$nlqYreYZ.HdeGfv1\$der3d6RRZNVzI14.7V45uOefryy8k8dN3YmaKqh/vv."

EXTRA_USERS_PARAMS = "\
    usermod -p '${PASSWD}' root; \
    usermod -p '${PASSWD}' user; \
"

