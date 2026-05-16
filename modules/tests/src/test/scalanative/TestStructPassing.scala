package bindgen

import org.junit.Assert.*
import org.junit.Test

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

class TestStructPassing:
  import lib_test_struct_passing.functions.*
  import lib_test_struct_passing.types.*
  @Test def test_arguments(): Unit =
    Zone {
      val struct1 = FunctionArg(42, c"universe")
      val struct2 = FunctionArg(128, c"yo")

      assertEquals(function_taking_struct1(!struct1), 42 * 42)
      assertEquals(function_taking_struct2(!struct1, !struct2), (42 * 128) / 2)

    }
  @Test def test_return(): Unit =
    Zone {

      val struct = function_returning_struct(115)

      assertEquals(struct.i, 115 * 115 - 1)

      val struct1 = FunctionArg(42, c"universe")
      val struct2 = FunctionArg(128, c"yo")
      val result = function_returning_and_taking_structs(struct1, struct2)

      assertEquals(result.i, 42 + 128)
      assertEquals(fromCString(result.str), "universeyo")


    }
end TestStructPassing
