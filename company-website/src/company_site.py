__author__ = 'Bobberclobber'

from flask import Flask, render_template

app = Flask(__name__)
app.debug = True

# The index page
@app.route('/')
def index():
    return render_template("index.html")

# The gallery page
@app.route('/gallery')
def gallery():
    return render_template("gallery.html")

if __name__ == '__main__':
    app.run(host='localhost')