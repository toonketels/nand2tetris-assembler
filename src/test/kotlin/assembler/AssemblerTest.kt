package assembler

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import java.lang.Error


class AssemblerSpec: FreeSpec({

    "no comments, whitespace nor symbols" - {
        val asm = this.javaClass.getResource("/1/Max.asm").readText()
        val hack = javaClass.getResource("/1/Max.hack").readText()

       convertText(asm) shouldBe hack
    }
    "with comments and whitespace, no symbols" - {
        val asm = this.javaClass.getResource("/2/Max.asm").readText()
        val hack = javaClass.getResource("/2/Max.hack").readText()

        convertText(asm) shouldBe hack
    }
    "with known address aliases" - {
        val asm = this.javaClass.getResource("/3/Max.asm").readText()
        val hack = javaClass.getResource("/3/Max.hack").readText()

        convertText(asm) shouldBe hack
    }
    "with label symbols" - {
        val asm = this.javaClass.getResource("/4/Max.asm").readText()
        val hack = javaClass.getResource("/4/Max.hack").readText()

        convertText(asm) shouldBe hack
    }
    "with variable symbols" - {
        val asm = this.javaClass.getResource("/5/Rect.asm").readText()
        val hack = javaClass.getResource("/5/Rect.hack").readText()

        convertText(asm) shouldBe hack
    }
    "even long program should work" - {
        val asm = this.javaClass.getResource("/6/Pong.asm").readText()
        val hack = javaClass.getResource("/6/Pong.hack").readText()

        convertText(asm) shouldBe hack
    }
    "errors should be reported" - {
        val asm = this.javaClass.getResource("/7/Rect.asm").readText()

        val exception = shouldThrow<Error> {
            convertText(asm)
        }
        exception.message shouldBe "Error line 25 'E=D+A'. Reason: Not a dest mnemonic"
    }
})








