require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Language
        q!: $regex</start>
        a: Выберите язык
        buttons:
            "Русский" -> /Russian
            "English" -> /English
            "中國人" -> /Chinese

    state: Russian
        a: Салют! Меня зовут Косуля! Я чат-бот разработчиков команды "Халявы не будет". Задайте интересующий Вас вопрос

        state: Hello
            intent: /sys/aimylogic/ru/hello
            a: Привет привет
    
        state: Bye
            intent: /до свидания
            a: До встречи!
    
        state: NoMatch
            event: noMatch
            a: Упс, вашего вопроса нет в списке часто задаваемых вопросов...
            go!: /Russian/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase/FAQ.Пустой шаблон
            script:
                $faq.pushReplies();
    
        state: noEmail
            a: К сожалению, я ничем не можем Вам помочь. Чтобы узнать ответ на вопрос напишите нам на почту: office@park-kosa.ru
    
        state: emailButtons
            a: Хотите чтобы я отправил ваш вопрос на почту службы поддержки?
            buttons:
                "Да" -> /Russian/inputQuestion
                "Нет" -> /Russian/noEmail
    
        state: inputQuestion
            InputText: 
                prompt = Введите, пожалуйста Ваш вопрос в свободной форме
                varName = question
                then = /Russian/inputEmail 

        state: inputEmail
            InputText: 
                prompt = Теперь введите, пожалуйста, Ваш e-mail, на который придёт ответ
                varName = mail
                then = /Russian/email 

        state: email
            Email:
                destination = listopad053@gmail.com
                subject = Вопрос бота
                text = Email отправителя: {{$session.mail}} \n {{$session.question}}
                files = []
                html = Email отправителя: {{$session.mail}} \n {{$session.question}}
                htmlEnabled = false
                okState = /Russian/email/success
        
            state: success
                a: Спасибо за Ваш вопрос, письмо успешно отправлено, ожидайте ответа на почту в течение 2 часов
    
    state: English
        a: Salute! My name is Kosulya! I am a chatbot for the developers of the “Khalyavy ne budet” team. Ask a question you are interested in

        state: Hello
            intent: /hello
            a: Hi!
    
        state: Bye
            intent: /bye
            a: Bye bye!
    
        state: NoMatch
            event: noMatch
            a: Oops, your question is not in the list of frequently asked questions...
            go!: /English/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase/FAQ.EnglishQ
            script:
                $faq.pushReplies();
    
        state: noEmail
            a: Unfortunately, I can't help you with anything. To find out the answer to your question, write to us by email: office@park-kosa.ru
    
        state: emailButtons
            a: Would you like me to send your question to the support email?
            buttons:
                "Yes" -> /English/inputQuestion
                "No" -> /English/noEmail
    
        state: inputQuestion
            InputText: 
                prompt = Please enter your question
                varName = question
                then = /English/inputEmail 

        state: inputEmail
            InputText: 
                prompt = Now please enter your e-mail to which you will receive a response
                varName = mail
                then = /English/email 

        state: email
            Email:
                destination = listopad053@gmail.com
                subject = Вопрос бота
                text = Email отправителя: {{$session.mail}} \n {{$session.question}}
                files = []
                html = Email отправителя: {{$session.mail}} \n {{$session.question}}
                htmlEnabled = false
                okState = /English/email/success
        
            state: success
                a: Thank you for your question, the letter was sent successfully, expect a response by email within 2 hours
    
    state: Chinese
        a: 禮炮！ 我的名字是科蘇利亞！ 我是「Khalyavy ne budet」團隊開發人員的聊天機器人。 問一個你感興趣的問題

        state: Hello
            intent: /sys/aimylogic/zh/hello
            a: 你好!
    
        state: Bye
            intent: /再見
            a: 再見！
    
        state: NoMatch
            event: noMatch
            a: 糟糕，您的問題不在常見問題清單中...
            go!: /Chinese/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase
            script:
                $faq.pushReplies();
    
        state: noEmail
            a: 不幸的是，我無法幫助你任何事。 要找到您問題的答案，請透過電子郵件寫信給我們：office@park-kosa.ru
    
        state: emailButtons
            a: 您希望我將您的問題發送到支援電子郵件嗎？
            buttons:
                "是的" -> /Chinese/inputQuestion
                "不" -> /Chinese/noEmail
    
        state: inputQuestion
            InputText: 
                prompt = 請輸入您的問題
                varName = question
                then = /Chinese/inputEmail 

        state: inputEmail
            InputText: 
                prompt = 現在請輸入您的電子郵件地址，您將收到回复
                varName = mail
                then = /Chinese/email 

        state: email
            Email:
                destination = listopad053@gmail.com
                subject = Вопрос бота
                text = Email отправителя: {{$session.mail}} \n {{$session.question}}
                files = []
                html = Email отправителя: {{$session.mail}} \n {{$session.question}}
                htmlEnabled = false
                okState = /Chinese/email/success
        
            state: success
                a: 感謝您的提問，信件已發送成功，預計2小時內透過電子郵件回覆

    state: NewState
        intent: /sys/aimylogic/ru/hello