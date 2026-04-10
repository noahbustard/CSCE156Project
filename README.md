# CSCE156 Project

This repository contains a Java and SQL-based sales database project built for **CSCE 156**. It focuses on modeling a realistic sales system with structured data, a relational schema, and a reusable Java API for interacting with the database.

## Overview

The project combines object-oriented Java with relational database design. It includes:

- a SQL schema for core business entities
- Java utility methods for inserting and managing records
- structured data files for stores, people, items, and sale data
- supporting design artifacts for database modeling

The overall goal is to represent a sales environment with customers, employees, stores, products, services, and subscription-style plans in a normalized database-backed system.

## Tech Stack

- Java
- SQL
- XML
- CSV
- Relational database design

## Repository Structure

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
                └── SalesData.java
```

## Core Components

### `Database.sql`
Defines the relational schema for the project. The database includes tables for entities such as:

- addresses
- people
- stores
- sales

The schema uses primary keys, foreign keys, and structured relationships to connect records across the system.

### `SalesData.java`
This file acts as the application-facing API for database operations. It is designed to support methods such as:

- clearing the database
- adding people and email records
- adding stores and managers
- adding items by type
- creating sales
- attaching products, leases, services, data plans, and voice plans to sales

This structure keeps business operations organized in code rather than scattering raw SQL logic throughout the project.

### Data Files
The project includes structured input files that support data loading, testing, and validation:

- `Items.xml`
- `Persons.xml`
- `Stores.xml`
- `SaleItems2.csv`

These files make it easier to seed the project with realistic sample data and test how the database handles different record types.

### `EER Diagram.png`
The EER diagram documents the intended database design and relationships between the main entities in the system.

## What This Project Demonstrates

This project reflects work in:

- object-oriented Java design
- relational schema design
- database normalization concepts
- building reusable data access utilities
- mapping business logic into code and tables
- working with multiple data formats for import and testing

## Key Concepts Modeled

The system is designed to handle a range of sale-related scenarios, including:

- customer and employee records
- store and manager relationships
- physical products
- service-based billable items
- data plans
- voice plans
- sales containing multiple item types

## Possible Improvements

Future improvements could include:

- finishing and validating all database utility methods
- adding stronger input validation and exception handling
- importing XML and CSV data automatically into SQL tables
- adding reporting and query features
- expanding test coverage for CRUD operations

## Authors

- Noah Bustard
- Caden France
