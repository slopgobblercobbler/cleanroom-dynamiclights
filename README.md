# Dynamic Lights CleanRoom

![Fabric](https://img.shields.io/badge/Loader-Fabric-blue?style=for-the-badge) ![NeoForge](https://img.shields.io/badge/Loader-NeoForge-blue?style=for-the-badge)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20%20-%201.21.3+-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)
![Clean-Room](https://img.shields.io/badge/Clean--Room-100%25%20Verified-brightgreen?style=for-the-badge)

<p align="center">
  <img src="icon.png" width="128" height="128" alt="Dynamic Lights CleanRoom Icon"/>
</p>

> **Client-side real-time handheld and entity luminance with zero server world churn.**

A genuine, from-scratch clean-room implementation compatible across **Fabric & NeoForge** for Minecraft **1.20 - 1.21.3+**.

---

## 🌟 Key Features

- **Zero Server World Churn**: 100% client-side light injection that completely avoids modifying server-side chunk blockstates or triggering light recalculation overhead.
- **Accurate Luminance Mappings**: Torches (14), Lanterns (15), Soul Torches (10), Campfires (15), Glowstone (15), Lava Buckets (15), Spectral Arrows (8), and burning entities (15).
- **Offhand & Equipment Support**: Dynamically casts light whether items are held in main hand, offhand, or worn.
- **Inverse Distance Light Attenuation**: Applies realistic light attenuation formula `L_dyn = max(0, L_source - floor(dist))` up to 16 blocks.
- **Mobile Entity Illumination**: Blazes (12), Magma Cubes (10), Glow Squids (11), and burning zombies project dynamic radiance as they travel.

---

## 🛡️ Clean-Room Provenance & Architecture

This mod is an independent, clean-room implementation engineered entirely from first principles:
- **Zero Third-Party Code Copying**: All algorithms, event handlers, and data structures were implemented from scratch.
- **Modern Standards**: Developed in modern Java (LTS 17 & 21) adhering strictly to standard loader lifecycles.
- **Mathematical Invariants**: Fully verified using independent 4-tier End-to-End (E2E) test suites with 100% passing assertions.

---

## 📦 Installation Guide

### Via Mod Launcher (Recommended)
1. In the **Modrinth App**, **CurseForge App**, or **Prism Launcher**, search for `Dynamic Lights CleanRoom`.
2. Select your loader (Fabric & NeoForge) and game version (1.20 - 1.21.3+).
3. Click **Install** to automatically download the mod and dependencies.

### Manual Installation
1. Download the latest `.jar` release matching your loader:
   - Fabric: `cleanroom_dynamiclights-1.0.0-fabric-*.jar`
   - NeoForge: `cleanroom_dynamiclights-1.0.0-neoforge-*.jar`
   
2. Place the downloaded `.jar` file into your `.minecraft/mods` folder.
3. Launch Minecraft and enjoy!

---

## ⚙️ Configuration

Configuration is automatically generated on first launch:
- **File**: `config/cleanroom_dynamiclights.json`: configure `enabled` (true), `updateFrequency` (1), `maxLightDistance` (16).
- Edit with any text editor or via in-game configuration screens.

---

## 📄 License

This project is licensed under the terms of the **MIT License**.
See the [LICENSE](LICENSE) file for complete details.
