__author__ = 'Josef'

from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.debug = True
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////tmp/test.db'
db = SQLAlchemy(app)

contact_info = [["Josef Fagerström", "josef.fagerstrom@hotmail.com", "+46 72-300 43 60"],
                ["Emil Nilsson", "e-mail", "tel-nr"],
                ["Olof Holmberg", "e-mail", "tel-nr"]]


class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True)
    email = db.Column(db.String(120), unique=True)

    def __init__(self, username, email):
        self.username = username
        self.email = email

    def __repr__(self):
        return '<User %r>' % self.username


# Index page
@app.route('/')
def index():
    return render_template("index.html")


@app.route('/products')
def products():
    return render_template("products.html")


@app.route('/kick_starters')
def kick_starters():
    return render_template("kick_starters.html", subscribed="")


# Contact page
@app.route('/contact')
def contact():
    return render_template("contact.html", contact_info=contact_info)


# When subscribing
# Add database functionality
@app.route('/subscribe', methods=['POST'])
def subscribe():
    email = request.form['email']
    if email == "":
        subscribed = "You have to enter an email address!"
    elif "@" not in email:
        subscribed = "That is not a valid email address!"
    else:
        subscribed = "Thanks for subscribing!"
    return render_template("kick_starters.html", subscribed=subscribed)


# Initializes the database
@app.route('/_init_db_')
def init_db():
    db.create_all()


if __name__ == '__main__':
    app.run(host='localhost')