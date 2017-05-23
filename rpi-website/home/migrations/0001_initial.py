# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2016-08-06 23:04
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_name', models.CharField(max_length=50)),
                ('email', models.CharField(max_length=50)),
                ('password', models.CharField(max_length=50)),
                ('join_date', models.DateTimeField(verbose_name='date joined')),
                ('permissions', models.IntegerField(default=0)),
            ],
        ),
    ]
