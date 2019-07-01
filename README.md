# Michael Checksum
A automatic file checksum validation tool for Windows.

This tool automatically watches specified filepaths on the filesystem. When a file is added a popup is presented to validate the new file.

## Development
1. Install JDK 1.8
2. Install MySql JDBC  Driver
3. Create Database for testing
    - Name: michael_checksum_dev
    - User: root
    - Password: `` (empty)
    - Run resources/Database/builddatabase.sql on newly created database

4. Create Database for runtime
    - Name: michael_checksum
    - User: root
    - Password: `` (empty)
    - Run resources/Database/builddatabase.sql on newly created database