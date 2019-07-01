# Michael Checksum
A automatic file checksum validation tool for Windows.

This tool automaticly watches specified filepaths on the filesystem. When a file is added a popup is presented to validate the new file.

## Development
1. Install JDK 1.8
2. Install MySql JDBC  Driver
3. Create Database
    - Name: michael_checksum_dev
    - User: root
    - Password: `` (empty)
4. Run resources/Database/builddatabase.sql on newly created database
