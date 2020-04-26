plugins {
    id("com.eden.orchidPlugin") version "0.20.0"
}

val orchidVersion = "0.20.0"

repositories {
    jcenter()
}

dependencies {
    orchidImplementation("io.github.javaeden.orchid:OrchidDocs:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidKotlindoc:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidPluginDocs:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidGithub:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidCopper:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidBsDoc:$orchidVersion")
    orchidRuntimeOnly("io.github.javaeden.orchid:OrchidSyntaxHighlighter:$orchidVersion")
}

orchid {
    args = listOf("--experimentalSourceDoc")
}
