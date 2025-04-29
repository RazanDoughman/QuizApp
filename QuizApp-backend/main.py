from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
import models, schemas, crud
from database import engine, SessionLocal
from typing import List

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.post("/create-quiz", response_model=schemas.Quiz)
def create_quiz(quiz: schemas.QuizCreate, db: Session = Depends(get_db)):
    return crud.create_quiz(db, quiz)

@app.post("/add-question", response_model=schemas.QuizQuestion)
def add_question(question: schemas.QuizQuestionCreate, db: Session = Depends(get_db)):
    return crud.add_question_to_quiz(db, question)

@app.get("/quizzes/category/{category_name}", response_model=List[schemas.Quiz])
def get_quizzes_by_category(category_name: str, db: Session = Depends(get_db)):
    quizzes = crud.get_quizzes_by_category(db, category_name)
    if not quizzes:
        raise HTTPException(status_code=404, detail="No quizzes found for this category")
    return quizzes

# Get user profile by email
@app.get("/profile/{email}", response_model=schemas.User)
def get_profile(email: str, db: Session = Depends(get_db)):
    profile = crud.get_profile(db, email)
    if not profile:
        raise HTTPException(status_code=404, detail="Profile not found")
    return profile

@app.post("/create-daily-quiz", response_model=schemas.DailyQuiz)
def create_daily_quiz(daily_quiz: schemas.DailyQuizBase, db: Session = Depends(get_db)):
    return crud.create_daily_quiz(db, daily_quiz)

@app.post("/profile", response_model=schemas.User)
def create_or_update_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    return crud.create_or_update_user(db, user)


@app.post("/spin", response_model=dict)
def spin_wheel(user_id: int, db: Session = Depends(get_db)):
    return crud.spin_wheel(db, user_id)





