SUMMARY = "The canonical example of init scripts"
SECTION = "base"
DESCRIPTION = "This recipe is a canonical example of init scripts"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYRIGHT;md5=349c872e0066155e1818b786938876a4"

SRC_URI = "file://skeleton.service \
	   file://skeleton_test.c \
	   file://COPYRIGHT \
	   "
inherit systemd

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}" 
SYSTEMD_SERVICE:${PN} = "skeleton.service"
#SYSTEMD_AUTO_ENABLE:${PN} = "enable"

S = "${WORKDIR}"

do_compile () {
	${CC} ${CFLAGS} ${LDFLAGS} ${WORKDIR}/skeleton_test.c -o ${WORKDIR}/skeleton-test
}

do_install () {
	install -d ${D}${base_libdir}/systemd/system
	cat ${WORKDIR}/skeleton.service | \
	  sed -e 's,/etc,${sysconfdir},g' \
	      -e 's,/usr/sbin,${sbindir},g' \
	      -e 's,/var,${localstatedir},g' \
	      -e 's,/usr/bin,${bindir},g' \
	      -e 's,/usr,${prefix},g' > ${D}${base_libdir}/systemd/system/skeleton.service

	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/skeleton-test ${D}${sbindir}/
}

CONFFILES:${PN} += "${base_libdir}/systemd/system/skeleton.service"
FILES:${PN} += "${base_libdir}/systemd"
