package assembler


fun convertText(asm: String): String {

    return asm
            .split('\n')
            .foldIndexed(Assembler()) { sourceLine, assembler, line -> assembler.readLine(sourceLine, line) }
            .toBinary()
            .joinToString("\n") + "\n"
}

private class Assembler() {

    private val instructions: MutableList<Line> = mutableListOf()
    private val symbolTable: SymbolTable = SymbolTable()

    fun readLine(sourceLine: Int, line: String): Assembler {

        val parsed = parseLine(sourceLine, instructions.size, line)

        when (parsed.instruction) {
            is ACode -> addInstruction(parsed)
            is CCode -> addInstruction(parsed)
            is Label -> addLabel(parsed.instruction)
        }

        return this
    }

    fun toBinary(): List<String> {
        return instructions.map { instruction ->
            try {
                instruction.instruction.toBinary(symbolTable)
            } catch (e: Exception) {
                throw Error("Error line ${instruction.sourceLine} '${instruction.original.trim()}'. Reason: ${e.message}")
            }
        }
    }

    private fun addInstruction(instruction: Line) {
        instructions.add(instruction)
    }

    private fun addLabel(label: Label) = symbolTable.addLabel(label.label, instructions.size)
}

