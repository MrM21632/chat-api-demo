package models

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Server struct {
	gorm.Model
	ID       uuid.UUID `gorm:"column:serverid;type:uuid;primary_key;default:gen_random_uuid()"`
	Name     string    `gorm:"column:server_name;unique"`
	Channels []Channel `gorm:"ForeignKey:ServerID"`
}

func (Server) TableName() string {
	return "server"
}
