#ifdef __cplusplus
extern "C" {
#endif

// declare Ruby registration method generated by SWIG in rholua_wrap.c
extern void Init_Rholua(void);

// this method executed once on start of program
void Init_Rholua_extension(void) {
	// execute initialization of Ruby registration (generated by SWIG)
	Init_Rholua();

	// You can add some code to this place:


}

#ifdef __cplusplus
} //extern "C"
#endif