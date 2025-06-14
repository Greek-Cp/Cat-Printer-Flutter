# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-01-XX

### Added
- Initial release of Lucky Printer Flutter library
- Support for all Lucky Printer models (D1, L1, A41, DP series, etc.)
- Bluetooth Low Energy (BLE) connectivity using flutter_blue_plus 1.35.5
- Device discovery and scanning functionality
- Print job management for text, images, and QR codes
- Printer status monitoring (battery, connection, state)
- Device control functions (paper feed, wake/sleep)
- Configurable print settings (density, speed)
- Comprehensive printer command set
- Real-time status updates via streams
- Error handling and logging
- Complete example application
- Detailed documentation and README

### Features
- **Device Discovery**: Scan and discover Lucky Printer devices
- **BLE Connectivity**: Connect to printers using flutter_blue_plus
- **Multiple Print Types**: Support for text, images, and QR codes
- **All Printer Models**: Support for 100+ Lucky Printer models
- **Print Settings**: Configurable density, speed, and paper settings
- **Status Monitoring**: Real-time printer status and battery monitoring
- **Device Control**: Paper feed, wake/sleep, and other device controls

### Supported Models
- D Series: LuckP_D1, LuckP_D1C, LuckP_D1S, LuckP_D1X, LuckP_D1W, LuckP_D1w
- L Series: LuckP_L1, LuckP_L1S, LuckP_L1F, LuckP_L2, LuckP_L3, LuckP_L4, LuckP_L80, LuckP_L81, LuckP_L90
- A Series: LuckP_A41, LuckP_A42, LuckP_A80, LuckP_A81
- DP Series: DP_D1, DP_D1H, DP_D2, DP_D2H, DP_D50, DP_D80, DP_D80H
- DP A Series: DP_A3, DP_A4, DP_A40, DP_A41, DP_A46, DP_A47, DP_A49, DP_A80, DP_A81
- DP L Series: DP_L1, DP_L1S, DP_L12, DP_L80, DP_L81, DP_L90, DP_L2Pro
- Special Models: Fichero, QIRUI_Q1, QIRUI_Q2, MMGG_G1, MMGG_G2, TPA46, TPA46Pro, APA41, APA46, APA46Y, C21E, SAM-02, PPS1, GrayPPS1
- And many more...

### Dependencies
- flutter_blue_plus: ^1.35.5
- permission_handler: ^11.0.1

### Platform Support
- Android (API 21+)
- iOS (iOS 12.0+)

## [Unreleased]

### Planned Features
- Classic Bluetooth support
- Image processing utilities
- Advanced QR code options
- Batch printing support
- Print queue management
- Custom printer profiles
- Print preview functionality
- Enhanced error recovery
- Performance optimizations 