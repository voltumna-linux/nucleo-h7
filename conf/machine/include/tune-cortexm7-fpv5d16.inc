require conf/machine/include/arm/armv7m/tune-cortexm7.inc

TUNEVALID[vfpv5d16] = "Enable VFPv5 double precision, 16 registers (fpv5-d16)."
TUNE_CCARGS_MFPU .= "${@bb.utils.contains('TUNE_FEATURES', 'vfpv5d16', 'fpv5-d16', '', d)}"

AVAILTUNES += "cortexm7-fpv5d16"
ARMPKGARCH:tune-cortexm7-fpv5d16 = "cortexm7"
TUNE_FEATURES:tune-cortexm7-fpv5d16 = "${TUNE_FEATURES:tune-cortexm7} vfpv5d16 callconvention-hard"
PACKAGE_EXTRA_ARCHS:tune-cortexm7-fpv5d16 = "${PACKAGE_EXTRA_ARCHS:tune-cortexm7} cortexm7hf-fpv5d16"
