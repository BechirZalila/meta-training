SUMMARY = "Exemple de recette qui hérite de la classe useradd"
DESCRIPTION = "Création d'un nouevel utilisateur user avec son dossier personne"
LICENSE = "MIT"
PR = "r1"

S = "${WORKDIR}"

inherit useradd

USERADD_PACKAGES = "${PN}"

# Crée un nouvel utilisateur "user" avec /home/user comme dossier personnel
# et /bin/sh comme shell par défaut
USERADD_PARAM:${PN} = "-u 1000 -d /home/user -r -s /bin/sh user"

do_install () {
    install -d -m 755 ${D}/home/user
    chown -R user ${D}/home/user
    chgrp -R user ${D}/home/user
}

FILES:${PN} = "/home/user"

