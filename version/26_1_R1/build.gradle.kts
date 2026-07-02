plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

// MC 26.1.2 ships Mojang-mapped with an unversioned craftbukkit package, so this module is built
// against the Paper dev bundle (paperweight) instead of the recraft plugin used by the legacy
// 1.x modules. recraft has no mappings for the new year-based Minecraft versioning.
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly(project(":ModernDisguise-API"))
    compileOnly("org.jetbrains:annotations:24.1.0")
    paperweight.paperDevBundle("26.1.2.build.70-stable")
}

// We never reobf — modern Paper runs Mojang-mapped, so the plain compiled jar is what links at runtime.
tasks.named("assemble") {
    dependsOn(tasks.named("jar"))
}
