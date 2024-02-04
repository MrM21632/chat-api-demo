import json
from flask import Blueprint, Response, jsonify, request

from db import db as database
from models.user import UserModel, UserSchema


bp = Blueprint("users", __name__, url_prefix="/users")


@bp.route("/", methods=("GET", "POST"))
def base_endpoint():
    if request.method == "POST":
        body = request.get_json()
        new_user = UserModel(**body)
        database.session.add(new_user)
        database.session.commit()
        return Response(json.dumps({"id": str(new_user.id)}), status=200, mimetype="application/json")

    return jsonify(UserSchema(many=True).dump(UserModel.query.all()))
