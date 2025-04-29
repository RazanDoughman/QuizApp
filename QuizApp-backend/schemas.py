from pydantic import BaseModel
from typing import List

class QuizBase(BaseModel):
    title: str
    description: str
    category: str


class QuizCreate(QuizBase):
    pass


class Quiz(QuizBase):
    id: int
    class Config:
        orm_mode = True


class QuizQuestionBase(BaseModel):
    question_text: str
    option_a: str
    option_b: str
    option_c: str
    option_d: str
    correct_option: str


class QuizQuestionCreate(QuizQuestionBase):
    quiz_id: int


class QuizQuestion(QuizQuestionBase):
    id: int
    quiz_id: int
    class Config:
        orm_mode = True


class DailyQuizBase(BaseModel):
    question_text: str
    option_a: str
    option_b: str
    option_c: str
    option_d: str
    correct_option: str


class DailyQuiz(DailyQuizBase):
    id: int
    class Config:
        orm_mode = True


class UserCreate(BaseModel):
    first_name: str
    last_name: str
    bio: str
    phone_number: str
    email: str


class User(BaseModel):
    id: int
    first_name: str
    last_name: str
    bio: str
    phone_number: str
    email: str
    points: int
    class Config:
        orm_mode = True

