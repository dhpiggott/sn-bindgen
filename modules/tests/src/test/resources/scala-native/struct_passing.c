#include "struct_passing.h"
#include <stdlib.h>
#include <string.h>

int function_taking_struct1(FunctionArg arg) { return arg.i * arg.i; }

int function_taking_struct2(FunctionArg arg1, FunctionArg arg2) {
  return (arg1.i * arg2.i) / 2;
}

FunctionArg function_returning_struct(int value) {
  // this leaks memory but for tests it should be okay
  FunctionArg mem = {value * value - 1, "hello"};

  return mem;
}

FunctionArg function_returning_and_taking_structs(FunctionArg arg1,
                                                  FunctionArg arg2) {
  int newI = arg1.i + arg2.i;
  unsigned len = strlen(arg1.str) + strlen(arg2.str);
  char *buf = malloc(len * sizeof(char) + 1);

  strncpy(buf, arg1.str, strlen(arg1.str));
  strncpy(buf + strlen(arg1.str), arg2.str, strlen(arg2.str));

  FunctionArg f;

  f.i = newI;
  f.str = buf;

  return f;
}
