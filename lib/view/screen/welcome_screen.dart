import 'package:flutter/material.dart';
import 'package:sportsgather/res/colors.dart';

class WelcomeScreen extends StatelessWidget {
  const WelcomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            color: SGColors.defaultBlack
        ),
        child: Column(
            children: [
              SizedBox(
                height: MediaQuery.of(context).size.height*3/5,
                child: PageView(
                  children: [1, 2, 3, 4, 5, 6, 7, 8].map((e) => Image.asset('asset/img/welcome/img_$e.jpg', fit: BoxFit.fitHeight,)).toList()
                ),
              ),
              Container(
                height: MediaQuery.of(context).size.height*2/5,
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: Column(
                  children: [
                    Spacer(),
                    Text(
                        "All information about Sports & eSports in one place",
                        style: TextStyle(color: SGColors.defaultWhite, fontSize: 26, fontWeight: FontWeight.bold)
                    ),
                    Spacer(),
                    Text(
                        "Read the news, follow matches, learn more about your favorite players",
                        style: TextStyle(color: SGColors.defaultWhite)
                    ),
                    Spacer(),
                    MaterialButton(
                      minWidth: double.infinity,
                      height: 60,
                      color: SGColors.defaultOrange,
                      textColor: SGColors.defaultWhite,
                      onPressed: (){

                      },
                      child: Text("SIGN UP"),
                    ),
                    Spacer(),
                    MaterialButton(
                      minWidth: double.infinity,
                      height: 60,
                      onPressed: (){},
                      textColor: SGColors.defaultWhite,
                      child: Text("LOG IN"),
                    ),
                    Spacer()
                  ],
                ),
              )
            ],
        ),
      ),
    );
  }
}
