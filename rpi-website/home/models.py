from django.db import models

class User(models.Model):
    user_name = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    join_date = models.DateTimeField('date joined')
    permissions = models.IntegerField(default=0)
