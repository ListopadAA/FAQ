require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Language
        q!: $regex</start>
        script:
            $session.Q = [];
        a: Выберите язык
        buttons:
            "Русский" -> /Russian/begin
            "English" -> /English/begin
            "中國人" -> /Chinese/begin
        

    state: Russian
        
        
        
        state: newQ
            a: Задайте интересующий вопрос
        
        state: begin
            a: Салют! Я - летучая мышка-Косичка, именно мой подвид проживает на Куршской косе и внесён в Красную книгу. Я чат-бот разработчиков команды "Халявы не будет". Задайте интересующий Вас вопрос

        state: censoring
            intent: /цензура
            a: Ой, ругаться плохо, попробуйте сформулировать запрос ещё разок
        
        state: spam
            intent: /спам
            a: Кажется, это спам...

        state: Hello
            intent: /привет
            a: Привет привет
    
        state: bye
            intent: /до свидания
            a: Спасибо, что обратились за помощью. Напомню Вам некоторые правила посещения Национального парка Куршская коса:
                \n* На территории парка запрещено мусорить
                \n* Нельзя разводить костры, ставить палатки в необорудованных местах
                \n* Нельзя наносить вред животным, растениям и различным объектам, находящимся на территории парка
                \n* На территории парка запрещены любые виды охоты и рыбалки
                \n* Если планируете посещать парк с собакой, позаботьтесь о том, чтобы ваша собака была на поводке
                \n* Также нельзя собирать грибы, ягоды и лекарственные растения
            a: Будем рады увидеть Вас в нашем заповеднике!
            a: До скорых встреч!
            
            a: Хотите сменить язык бота?
            buttons:
                "Продолжить на русском" -> /Russian/newQ
                "Сменить язык" -> /Language
    
        state: NoMatch
            event: noMatch
            if: $session.Q.indexOf($request.query) > -1
                a: Кажется, Вы мне спамите...
                go!: /Russian
            a: Упс, вашего вопроса нет в списке часто задаваемых вопросов...
            script:
                $session.Q.push(String($request.query));
            
            go!: /Russian/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase/FAQ.Пустой шаблон
            script:
                $faq.pushReplies();
            go!: /Russian/bye
    
        state: noEmail
            a: К сожалению, я ничем не могу Вам помочь. Чтобы узнать ответ на вопрос напишите нам на почту: office@park-kosa.ru
            go!: /Russian/bye
    
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
                text = Доброго времени суток! Меня заинтересовал следующий вопрос: {{$session.question}}\n\nПрошу отправить мне ответ на почту: {{$session.mail}}\nСпасибо! Всего хорошего!
                files = []
                html = Email отправителя: {{$session.mail}} \n {{$session.question}}
                htmlEnabled = false
                okState = /Russian/email/success
        
            state: success
                a: Письмо успешно отправлено, ожидайте ответа на почту в течение 2 часов
                go!: /Russian/bye
                
    
    state: English
        
        state: begin
            a: Salute! I am the bat-Kosichka, it is my subspecies that lives on the Curonian Spit and is listed in the Red Book. I am a chatbot for the developers of the “Khalyavy ne budet” team. Ask a question you are interested in

        state: Hello
            intent: /hello
            a: Hi!
    
        state: bye
            intent: /bye
            a: Thank you for asking for help. Let me remind you of some rules for visiting the Curonian Spit National Park:
                \n* Littering is prohibited in the park
                \n* You cannot make fires or put up tents in unequipped places
                \n* Do not harm animals, plants and various objects located in the park
                \n* Any type of hunting and fishing is prohibited in the park
                \n* If you plan to visit the park with a dog, make sure your dog is on a leash
                \n* You also cannot collect mushrooms, berries and medicinal plants
            a: We will be glad to see you in our reserve!
            a: See you soon!
    
        state: NoMatch
            event: noMatch
            a: Oops, your question is not in the list of frequently asked questions...
            go!: /English/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase/FAQ.EnglishQ
            script:
                $faq.pushReplies();
            go!: /English/bye
    
        state: noEmail
            a: Unfortunately, I can't help you with anything. To find out the answer to your question, write to us by email: office@park-kosa.ru
            go!: /English/bye
    
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
                go!: /English/bye
    
    state: Chinese
        
        state: begin
            a: 禮炮！ 我是蝙蝠 Kosichka，它是我的亞種，生活在庫爾斯沙嘴上，並被列入紅皮書。 我是「Khalyavy ne budet」團隊開發人員的聊天機器人。 問一個你感興趣的問題

        state: Hello
            intent: /sys/aimylogic/zh/hello
            a: 你好!
    
        state: bye
            intent: /再見
            a: 感謝您尋求協助。 讓我提醒您參觀庫爾斯沙嘴國家公園的一些規則：
                \n* 公園內禁止亂丟垃圾
                \n* 不能在沒有裝備的地方生火或搭帳篷
                \n* 請勿傷害公園內的動物、植物和各種物體
                \n* 公園內禁止任何形式的狩獵和釣魚
                \n* 如果您打算帶狗去公園，請確保您的狗拴著皮帶
                \n* 你也不能採集蘑菇、漿果和藥用植物
            a: 我們很高興在我們的保護區見到您！
            a: 再見！
    
        state: NoMatch
            event: noMatch
            a: 糟糕，您的問題不在常見問題清單中...
            go!: /Chinese/emailButtons
    
        state: KnowledgeBase
            intentGroup: /KnowledgeBase
            script:
                $faq.pushReplies();
            go!: /Chinese/bye
    
        state: noEmail
            a: 不幸的是，我無法幫助你任何事。 要找到您問題的答案，請透過電子郵件寫信給我們：office@park-kosa.ru
            go!: /Chinese/bye
    
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
                go!: /Chinese/bye
