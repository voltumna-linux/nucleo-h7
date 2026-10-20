SUMMARY = "Open source ST-LINK utility"
DESCRIPTION = "Open source ST-LINK v2 and v3 tools: st-flash, st-util, st-info, \
st-trace, st-server"
HOMEPAGE = "https://github.com/stlink-org/stlink"
BUGTRACKER = "https://github.com/stlink-org/stlink/issues"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3374725a40df7047d33d627811b8149e"

SRC_URI = "git://github.com/stlink-org/stlink;branch=master;protocol=https;tag=v${PV} \
           file://0001-cmake-include-modules-by-name-to-survive-OE-toolchain.patch \
           "

inherit cmake pkgconfig

DEPENDS = "libusb1"

# stlink-gui is only built when gtk+-3.0 is found via pkg-config: keep it out
# of the SDK, we only want the command line tools
EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"

do_install:append:class-nativesdk () {
    # Upstream installs modprobe.d and udev rules to hardcoded /etc and /lib,
    # outside the remapped nativesdk prefix: unneeded inside the SDK, drop them
    rm -rf ${D}/etc ${D}/lib
    # The tool install() rules also copy the shared library next to the
    # binaries via RUNTIME_DEPENDENCY_SET, duplicating the ${libdir} copy and
    # making the package a duplicate shlib provider for itself
    rm -f ${D}${bindir}/libstlink.so*
}

BBCLASSEXTEND = "native nativesdk"
