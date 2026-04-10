# CSCE156 Project

Java and SQL-based sales database project for CSCE 156.

## Overview

This project focuses on building a structured sales system backed by a relational database. It includes Java code for interacting with the database, SQL schema creation, and sample data files for stores, people, and items.

The goal of the project is to model a realistic sales environment with customers, employees, stores, products, services, and plans, then provide a clean API for loading and working with that data.

## Features

- relational database schema for core business entities
- Java utility methods for inserting and managing data
- support for:
  - people and email records
  - stores and managers
  - items such as products, services, data plans, and voice plans
  - sales and sale line items
- sample XML and CSV data for populating the system
- EER diagram for database design

## Project Structure

```text
CSCE156Project/
├── Database.sql
├── EER Diagram.png
├── SaleItems2.csv
└── AssignmentTwo/
    ├── README.md
    ├── data/
    │   ├── Items.xml
    │   ├── Persons.xml
    │   └── Stores.xml
    └── src/
        └── com/
            └── yrl/
                └── SalesData.java```
Tech Stack
Java
SQL
XML
CSV
Relational Database Design
Core Components
Database.sql

Defines the database schema for the project, including tables for addresses, people, and sales, along with foreign key relationships.

SalesData.java

Provides a set of utility methods that form the application API for interacting with the database. These methods are designed for tasks such as:

clearing the database
adding people and email addresses
adding stores
adding items
creating sales
attaching products, leases, services, data plans, and voice plans to sales
Data Files

The project includes structured data files for importing or testing:

Items.xml
Persons.xml
Stores.xml
SaleItems2.csv
What This Project Demonstrates
object-oriented Java design
working with relational schemas and foreign keys
modeling business logic in code
separating data storage from application logic
building reusable database access utilities
Future Improvements
implement all remaining database methods
add validation and error handling
support automated data import from XML / CSV into SQL
add query/reporting features
add test coverage for insertion and retrieval methods
Authors
Noah Bustard
Caden France
