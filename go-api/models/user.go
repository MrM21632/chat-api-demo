package models

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type User struct {
	gorm.Model
	ID       uuid.UUID `gorm:"column:userid;type:uuid;primary_key;default:gen_random_uuid()"`
	Username string    `gorm:"column:username;size:32;unique"`
	Email    string    `gorm:"column:email;unique"`
	Password string    `gorm:"column:password"`
	PassSalt string    `gorm:"column:passsalt"`
}

func (User) TableName() string {
	return "useracct"
}
