package assembler


internal interface ToBinary {
    fun toBinary(symbols: SymbolTable): String
    fun isBinary(): Boolean = true
}

internal data class Line(val sourceLine: Int, val hackLine: Int, val instruction: ToBinary, val original: String)

internal data class Label(val label: String) : ToBinary {

    override fun toBinary(symbols: SymbolTable) = ""

    override fun isBinary() = false
}

internal data class NoCode(val input: String? = null) : ToBinary {
    override fun toBinary(symbols: SymbolTable): String = ""

    override fun isBinary() = false
}

internal data class CCode(val comp: String, val dest: String? = null, val jump: String? = null) : ToBinary {

    override fun toBinary(symbols: SymbolTable): String {
        return buildString {
            // 1 opcode for c instructions and 11 for fillers
            append("111")

            comp.let {
                when (it) {
                    "0" -> "0101010"
                    "1" -> "0111111"
                    "-1" -> "0111010"
                    "D" -> "0001100"
                    "A" -> "0110000"
                    "!D" -> "0001101"
                    "!A" -> "0110001"
                    "-D" -> "0001111"
                    "-A" -> "0110011"
                    "D+1" -> "0011111"
                    "A+1" -> "0110111"
                    "D-1" -> "0001110"
                    "A-1" -> "0110010"
                    "D+A" -> "0000010"
                    "D-A" -> "0010011"
                    "A-D" -> "0000111"
                    "D&A" -> "0000000"
                    "D|A" -> "0010101"

                    "M" -> "1110000"
                    "!M" -> "1110001"
                    "-M" -> "1110011"
                    "M+1" -> "1110111"
                    "M-1" -> "1110010"
                    "D+M" -> "1000010"
                    "D-M" -> "1010011"
                    "M-D" -> "1000111"
                    "D&M" -> "1000000"
                    "D|M" -> "1010101"
                    else -> throw IllegalArgumentException("Not a comp mnemonic")
                }
            }.also { append(it) }

            dest.let {
                when (it) {
                    null -> "000"
                    "M" -> "001"
                    "D" -> "010"
                    "MD" -> "011"
                    "A" -> "100"
                    "AM" -> "101"
                    "AD" -> "110"
                    "AMD" -> "111"
                    else -> throw IllegalArgumentException("Not a dest mnemonic")
                }
            }.also { append(it) }

            jump.let {
                when (it) {
                    null -> "000"
                    "JGT" -> "001"
                    "JEQ" -> "010"
                    "JGE" -> "011"
                    "JLT" -> "100"
                    "JNE" -> "101"
                    "JLE" -> "110"
                    "JMP" -> "111"
                    else -> throw IllegalArgumentException("Not a jump mnemonic")
                }
            }.also { append(it) }
        }
    }
}

internal data class ACode(val address: String) : ToBinary {

    override fun toBinary(symbols: SymbolTable): String = buildString {
        // a op code
        append("0")

        val resolvedAddress = try { address.toInt() } catch (e: Exception) { symbols.getSymbol(address).toInt() }
        resolvedAddress.toString(radix = 2)
                .let { it.padStart(15, '0') }
                .also { append(it) }
    }
}