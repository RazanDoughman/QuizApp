from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import os

# Using environment variable for the database URL (set in Vercel)
SQLALCHEMY_DATABASE_URL = "postgresql://fastapi_postgresql_rc8w_user:XbdHAAmmresEJ4xnnSBYbkXMd5gnLZnS@dpg-cta5ul8gph6c73ekqk10-a.oregon-postgres.render.com/fastapi_postgresql_rc8w"
#SQLALCHEMY_DATABASE_URL = "sqlite:///./sql_app.db"
# Initialize the database engine

engine = create_engine(SQLALCHEMY_DATABASE_URL)

# Session maker and base model
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()
