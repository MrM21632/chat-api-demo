package models

import (
	"github.com/google/uuid"
)

type User struct {
	ID       uuid.UUID `gorm:"column:userid;type:uuid;primary_key;default:gen_random_uuid()"`
	Username string    `gorm:"column:username;size:32;unique"`
	Email    string    `gorm:"column:email;unique"`
	Password string    `gorm:"column:password"`
	PassSalt string    `gorm:"column:passsalt"`
}

type CreateUserInput struct {
	Username string `json:"username" binding:"required"`
	Email    string `json:"email" binding:"required"`
	Password string `json:"passhash" binding:"required"`
	PassSalt string `json:"passsalt" binding:"required"`
}

func (User) TableName() string {
	return "chat_data.useracct"
}
