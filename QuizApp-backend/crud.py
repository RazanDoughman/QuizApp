from sqlalchemy.orm import Session
import models, schemas
from datetime import datetime, timedelta


def get_quizzes_by_category(db: Session, category_name: str):
    return db.query(models.Quiz).filter(models.Quiz.category == category_name).all()


def create_quiz(db: Session, quiz: schemas.QuizCreate):
    db_quiz = models.Quiz(
        title=quiz.title, description=quiz.description, category=quiz.category
    )
    db.add(db_quiz)
    db.commit()
    db.refresh(db_quiz)
    return db_quiz


def add_question_to_quiz(db: Session, question: schemas.QuizQuestionCreate):
    db_question = models.QuizQuestion(
        quiz_id=question.quiz_id,
        question_text=question.question_text,
        option_a=question.option_a,
        option_b=question.option_b,
        option_c=question.option_c,
        option_d=question.option_d,
        correct_option=question.correct_option,
    )
    db.add(db_question)
    db.commit()
    db.refresh(db_question)
    return db_question


def create_daily_quiz(db: Session, daily_quiz: schemas.DailyQuizBase):
    db_quiz = models.DailyQuiz(
        question_text=daily_quiz.question_text,
        option_a=daily_quiz.option_a,
        option_b=daily_quiz.option_b,
        option_c=daily_quiz.option_c,
        option_d=daily_quiz.option_d,
        correct_option=daily_quiz.correct_option,
    )
    db.add(db_quiz)
    db.commit()
    db.refresh(db_quiz)
    return db_quiz


def get_profile(db: Session, email: str):
    return db.query(models.User).filter(models.User.email == email).first()


def create_or_update_user(db: Session, user: schemas.UserCreate):
    db_user = db.query(models.User).filter(models.User.email == user.email).first()
    if db_user:
        db_user.first_name = user.first_name
        db_user.last_name = user.last_name
        db_user.bio = user.bio
        db_user.phone_number = user.phone_number
        db.commit()
        db.refresh(db_user)
        return db_user
    else:
        db_user = models.User(**user.dict())
        db.add(db_user)
        db.commit()
        db.refresh(db_user)
        return db_user


def spin_wheel(db: Session, user_id: int):
    user = db.query(models.User).filter(models.User.id == user_id).first()
    if not user:
        raise ValueError("User not found")
    now = datetime.now()
    if user.last_spin and now - user.last_spin < timedelta(hours=24):
        raise ValueError("Spin available only every 24 hours.")
    user.last_spin = now
    user.points += 100  
    db.commit()
    return {"message": "Spin successful", "points": user.points}




    
