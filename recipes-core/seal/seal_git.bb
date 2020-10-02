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

PV = "${SRCPV}"

SRC_URI = "git://github.com/microsoft/SEAL.git;protocol=http"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

PACKAGES = "${PN}-dbg ${PN} ${PN}-doc ${PN}-dev ${PN}-staticdev ${PN}-locale"

FILES_${PN} = "\
    ${bindir}/* \
    ${sbindir}/* \
    ${libexecdir}/* \
    /usr/local/lib/lib*.so.* \
    ${sysconfdir} \
    ${sharedstatedir} \
    ${localstatedir} \
    /bin/* \
    /sbin/* \
    /lib/*.so* \
    ${datadir}/${PN} \
    /usr/local/lib/${PN}/* \
    ${datadir}/pixmaps \
    ${datadir}/applications \
    ${datadir}/idl \
    ${datadir}/omf \
    ${datadir}/sounds \
    /usr/local/lib/bonobo/servers"

FILES_${PN}-dbg = "\
    ${bindir}/.debug \
    ${sbindir}/.debug \
    ${libexecdir}/.debug \
    /usr/local/lib/.debug \
    /bin/.debug \
    /sbin/.debug \
    /lib/.debug \
    /usr/local/lib/${PN}/.debug"

FILES_${PN}-doc = "\
    ${docdir} \
    ${mandir} \
    ${infodir} \
    ${datadir}/gtk-doc \
    ${datadir}/gnome/help"

FILES_${PN}-dev = "\
    /usr/local/include \
    /usr/src \
    /usr/local/lib/lib*.so \
    /usr/local/lib/*.la \
    /usr/local/lib/*.o \
    /usr/local/lib/pkgconfig \
    /usr/local/lib/cmake \
    /lib/*.a \
    /lib/*.o \
    ${datadir}/aclocal"

FILES_${PN}-locale = "${datadir}/locale"

FILES_${PN}-staticdev = "\
    /usr/local/lib/*.a"

inherit cmake autotools pkgconfig

do_compile_prepend() {
  cmake ../git -DBUILD_SHARED_LIBS=OFF -DCMAKE_BUILD_TYPE=Release
}

#do_package_qa_prepend() {
#  rm ../packages-split/seal-dev/usr/local/lib/libseal.so
#  ln -s ../packages-split/seal/usr/local/lib/libseal.so.3.5.9 ../packages-split/seal-dev/usr/local/lib/libseal.so
#}
