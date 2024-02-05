package models

import (
	"github.com/google/uuid"
)

type Server struct {
	ID       uuid.UUID `gorm:"column:serverid;type:uuid;primary_key;default:gen_random_uuid()"`
	Name     string    `gorm:"column:server_name;unique"`
	Channels []Channel `gorm:"ForeignKey:ServerID"`
}

func (Server) TableName() string {
	return "chat_data.server"
}
