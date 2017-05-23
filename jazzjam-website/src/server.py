from flask import Flask, render_template


app = Flask(__name__)

@app.route('/')
@app.route('/home')
@app.route('/index')
def index():
    return render_template('index.html')


@app.route('/about')
def about():
    return "about"


@app.route('/program')
def program():
    return "program"


@app.route('/gallery')
def gallery():
    return "gallery"


@app.route('/contact')
def contact():
    return render_template('contact.html')


if __name__ == "__main__":
    app.run("localhost", debug=True)
