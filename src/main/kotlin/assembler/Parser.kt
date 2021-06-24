package assembler

internal fun parseLine(sourceLine: Int, pc: Int, line: String): Line = Line(sourceLine, pc, parseLine(line), line)

private fun parseLine(line: String): ToBinary {

    // strip comments
    var (withoutComments) = line.split("//")

    var line = withoutComments.trim();

    if (line.isBlank() || line.startsWith("//")) {
        return NoCode(line)
    }

    if (line.startsWith("(")) {
        return Label(line.removeSurrounding("(", ")"))
    }

    if (line.startsWith("@")) {
        return ACode(line.substring(1))
    }

    if (line.contains("=") && line.contains(";")) {

        val (dest, comp, jump) = line.split("=", ";")
        return CCode(comp, dest, jump);
    }

    if (line.contains("=")) {
        val (dest, comp) = line.split("=")
        return CCode(comp, dest)
    }

    if (line.contains(";")) {
        val (comp, jump) = line.split(";")
        return CCode(comp, jump = jump)
    }

    return CCode(line)
}