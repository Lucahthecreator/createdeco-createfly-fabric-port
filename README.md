# Create Deco - CreateFly Fabric Port

A Fabric/CreateFly port of Create Deco for Minecraft 26.x.

This project ports the original Create Deco mod to the CreateFly Fabric ecosystem and currently targets:

- Minecraft 26.1.2 with Create 6.0.9 build 4
- Minecraft 26.2 with Create 6.0.9 build 1

## Original Projects

- Original Create Deco: https://github.com/talrey/CreateDeco
- CreateFly on CurseForge: https://www.curseforge.com/minecraft/mc-mods/create-fly

This port is not the original Create Deco project. Credit for the original mod, assets, and design goes to the Create Deco authors.

## Install

1. Install Fabric Loader for the Minecraft version you want to use.
2. Install Fabric API for that Minecraft version.
3. Install CreateFly/Create for that Minecraft version.
4. Put the matching Create Deco CreateFly jar in your `mods` folder.
5. Launch the game.

Use the jar that matches your Minecraft version:

- `createdeco-createfly-2.1.2-createfly+mc.26.1.2.jar`
- `createdeco-createfly-2.1.2-createfly+mc.26.2.jar`

Do not mix the 26.1.2 jar into a 26.2 pack, or the 26.2 jar into a 26.1.2 pack.

## Current Port Notes

- Fabric/CreateFly port cleanup.
- NeoForge-only code removed or replaced where needed.
- Create Deco creative tabs restored and sorted by type.
- Doors, trapdoors, catwalks, railings, bars, placards, support wedges, and shipping containers have port-specific fixes.
- Shipping containers support connected textures for same-color/same-axis multi-block containers.
- Placards use Create's placard behavior without crashing on placement.

## Build

Build for Minecraft 26.1.2:

```bash
./gradlew build -Ptarget_version=26.1.2
```

Build for Minecraft 26.2:

```bash
./gradlew build -Ptarget_version=26.2
```

On Windows:

```powershell
.\gradlew.bat build '-Ptarget_version=26.1.2'
.\gradlew.bat build '-Ptarget_version=26.2'
```

Built jars are created in `build/libs`.

## Contributing

Issues and pull requests are welcome. Please include:

- Minecraft version
- CreateFly/Create version
- Fabric API version
- A screenshot or crash log if the issue is visual or crashes
- Steps to reproduce

## License

This port follows the license included in this repository. Original Create Deco code and assets remain credited to the original project.
