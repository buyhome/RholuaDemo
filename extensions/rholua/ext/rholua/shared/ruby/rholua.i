/* rholua.i */
%module Rholua
%{
#include "ruby/ext/rho/rhoruby.h"

extern VALUE rholua_native_process_string(const char* str);
extern int rholua_calc_summ(int x, int y);

#define native_process_string rholua_native_process_string 
#define calc_summ rholua_calc_summ 

%}

extern VALUE native_process_string(const char* str);
extern int calc_summ(int x, int y);
