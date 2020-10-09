SUMMARY = "Microsoft SEAL is an open-source homomorphic encryption library."

DESCRIPTION = "Microsoft SEAL is an easy-to-use open-source \
(MIT licensed) homomorphic encryption library developed by \
the Cryptography and Privacy Research group at Microsoft. \
Microsoft SEAL is written in modern standard C++ and is \
easy to compile and run in many different environments. \
For more information about the Microsoft SEAL project, \
see sealcrypto.org."

HOMEPAGE = "https://www.microsoft.com/en-us/research/project/microsoft-seal/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b98fddd052bb2f5ddbcdbd417ffb26a8" 

#inherit autotools cmake pkgconfig
inherit cmake

SRC_URI = "git://github.com/microsoft/SEAL.git;protocol=http"
SRCREV = "${AUTOREV}"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS = "zlib"

#PACKAGES = "${PN}-dbg ${PN} ${PN}-doc ${PN}-dev ${PN}-staticdev ${PN}-locale"

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON -DCMAKE_BUILD_TYPE=Release -DSEAL_USE_ZLIB=OFF -DSEAL_USE_MSGSL=OFF"
EXTRA_OECMAKE += "-DCMAKE_CROSSCOMPILING=OFF"
OECMAKE_BUILDPATH += "${WORKDIR}/build"
OECMAKE_SOURCEPATH += "${S}"

FILES_${PN} = "\
    ${bindir}/* \
    ${sbindir}/* \
    ${libexecdir}/* \
    /usr/lib/lib*.so.* \
    ${sysconfdir} \
    ${sharedstatedir} \
    ${localstatedir} \
    /bin/* \
    /sbin/* \
    /lib/*.so* \
    ${datadir}/${PN} \
    /usr/lib/${PN}/* \
    ${datadir}/pixmaps \
    ${datadir}/applications \
    ${datadir}/idl \
    ${datadir}/omf \
    ${datadir}/sounds \
    /usr/lib/bonobo/servers"

FILES_${PN}-dbg = "\
    ${bindir}/.debug \
    ${sbindir}/.debug \
    ${libexecdir}/.debug \
    /usr/local/lib/.debug \
    /bin/.debug \
    /sbin/.debug \
    /lib/.debug \
    /usr/lib/${PN}/.debug"

FILES_${PN}-doc = "\
    ${docdir} \
    ${mandir} \
    ${infodir} \
    ${datadir}/gtk-doc \
    ${datadir}/gnome/help"

FILES_${PN}-dev = "\
    /usr/include \
    /usr/src \
    /usr/lib/lib*.so \
    /usr/lib/*.la \
    /usr/lib/*.o \
    /usr/lib/pkgconfig \
    /lib/*.a \
    /lib/*.o \
    ${datadir}/aclocal"

# /usr/lib/cmake

FILES_${PN}-locale = "${datadir}/locale"

FILES_${PN}-staticdev = "\
    /usr/lib/*.a"

do_configure_prepend() {
  cd ${S}
#  cmake --version
#  g++ --version
#  ls -a
}

do_install_append() {
  rm -rf ${D}${libdir}/cmake
}
