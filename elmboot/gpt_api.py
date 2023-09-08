# -*- coding: utf-8 -*-
from revChatGPT.V1 import Chatbot
import sys

chatbot = Chatbot(config={
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJ4d3ozNjU3NkBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZX0sImh0dHBzOi8vYXBpLm9wZW5haS5jb20vYXV0aCI6eyJ1c2VyX2lkIjoidXNlci1OWnpIbDRCb1VlZFVTYVZqVzlWNklLMkQifSwiaXNzIjoiaHR0cHM6Ly9hdXRoMC5vcGVuYWkuY29tLyIsInN1YiI6Imdvb2dsZS1vYXV0aDJ8MTE0OTk3NjY0MTgwNzc5OTY3NTI1IiwiYXVkIjpbImh0dHBzOi8vYXBpLm9wZW5haS5jb20vdjEiLCJodHRwczovL29wZW5haS5vcGVuYWkuYXV0aDBhcHAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY5MzkzNDMzNSwiZXhwIjoxNjk1MTQzOTM1LCJhenAiOiJUZEpJY2JlMTZXb1RIdE45NW55eXdoNUU0eU9vNkl0RyIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9yZ2FuaXphdGlvbi53cml0ZSBvZmZsaW5lX2FjY2VzcyJ9.ffM49byXhY9gCRy8EjpONc85wAq_D2axWFNVZPi5jdNPZLS9PjHyKpxLZXKo9lycUQCZpdFBwkX9CFCtcAwnvyyd_vdBi2pLRiAFyLNr6AhYVOnV7PacTyXori2467Hy8HhmpI-bDOKlkgJr5UbekWM6fiRh89ABnMwwWlkxANs3xK_lR-0Q-wD3clcuAx3ForGu9cYufN6AvQ3YSO0D5sJMV78FDO-m7y_rn-xNLYmfMTEbjIj7K_W015clnw2HYbJXZHihL4DsOQQO-PlXM4cezZAntxO5bLNpR4xattleXzhQZQ1fBtbrgVNq5h_mWWj21OSj8SJ_SIZ6xKh8-g"
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
