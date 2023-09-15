# -*- coding: utf-8 -*-
from revChatGPT.V1 import Chatbot
import sys

chatbot = Chatbot(config={
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJ4eDI1MDk3NzU5MjlAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJodHRwczovL2FwaS5vcGVuYWkuY29tL2F1dGgiOnsidXNlcl9pZCI6InVzZXItTUUwdFFreVZtVWZ0RHlvNFhpWEVoclBXIn0sImlzcyI6Imh0dHBzOi8vYXV0aDAub3BlbmFpLmNvbS8iLCJzdWIiOiJnb29nbGUtb2F1dGgyfDExNTg0MTA1OTMxOTcyNTY3MjUzMSIsImF1ZCI6WyJodHRwczovL2FwaS5vcGVuYWkuY29tL3YxIiwiaHR0cHM6Ly9vcGVuYWkub3BlbmFpLmF1dGgwYXBwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2OTI5NTU1NjIsImV4cCI6MTY5NDE2NTE2MiwiYXpwIjoiVGRKSWNiZTE2V29USHROOTVueXl3aDVFNHlPbzZJdEciLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG1vZGVsLnJlYWQgbW9kZWwucmVxdWVzdCBvcmdhbml6YXRpb24ucmVhZCBvcmdhbml6YXRpb24ud3JpdGUgb2ZmbGluZV9hY2Nlc3MifQ.bCMk29HlIENeT6CZTi502j6aAqpsMsgsvzCYKW7ykzY-XPHIYFikrX3CzJPixXZgY0DZrjwZyes_eags5PQsfKuU8jVZc8sb_WjmwPIqkO2AED2RbnyQr2V9UQA-FynfxJPPyQu-C0yVaoYFCdxB3-Y21O-ftGut56Bg06JB7SmmVwuW4_3xcTGWFv4EaFDqKu6WbsmKIXSex-LHdG9YjVX-DQaTYsUyU29owALAbFl5w6uFO6g41rQ2fvGl0-3nhAOCu9td6fKxMtwS7iTN3yl4yp1A0BNnFCJKw6JvmcfSdooYj5wkalr2birN2AGLUH71Pnazf2lRJ8Dj8F1n8w"
})

if len(sys.argv) == 2:

    # adj = input("请输入一个形容词：")
    adj = sys.argv[1]

    if adj == "":
        prompt = "我想吃中餐，你有什么推荐的吗，使用最少的中文单词列出十个选项，不要回答任何多余的话"
    else:
        prompt = "我想吃" + adj + "的中餐，你有什么推荐的吗，使用最少的中文单词列出十个选项，不要回答任何多余的话"
        response = ""

    for data in chatbot.ask(
        prompt
    ):
        response = data["message"]

    # 仅保留response中以数字开头的行
    response = "\n".join(
        [line for line in response.split("\n") if line[0].isdigit()])
    print(response)

else:
    question = sys.argv[1]
    conversation_id = sys.argv[2]
    if conversation_id == "null":
        for data in chatbot.ask(question):
            response = data["message"]
        print(response + "&" + data["conversation_id"])
    else:
        for data in chatbot.ask(question, conversation_id):
            response = data["message"]
        print(response + "&" + data["conversation_id"])
