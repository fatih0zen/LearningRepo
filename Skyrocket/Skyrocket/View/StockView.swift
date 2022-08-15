//
//  StockView.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 1/13/22.
//

import SwiftUI

struct StockView: View {
    @ObservedObject var viewModel: SkyrocketViewModel
    var stockSymbol = "AAPL"
    
    var body: some View {
        VStack {
            ZStack {
                ScrollView(showsIndicators: false) {
                    VStack {
                        companyLogoAndPrice
                        
                        Button("Get Details") {
                            viewModel.search(stockSymbol)
                            viewModel.getCompanyDetails(stockSymbol)
                            viewModel.getCurrentPrice(stockSymbol)
                            viewModel.setUpLineChart(stockSymbol)
                            viewModel.getStockFinancials(stockSymbol)
                            viewModel.getMarketData(stockSymbol)
                            viewModel.getStockQuote(stockSymbol)
                            viewModel.getNews()
                            viewModel.getNews(for: stockSymbol)
                            viewModel.getNews(for: stockSymbol, limit: 1)
                        }
                        
                        aboutCompany
                        companyStats
                    }
                }
            }
        }
    }
    
    var companyLogoAndPrice: some View {
        HStack(alignment: .center) {
            AsyncImage(url: URL(string: viewModel.companyLogoUrl))
                .frame(width: 94, height: 94)
                .cornerRadius(25)
            VStack(alignment: .leading) {
                Group {
                    Text(viewModel.companyTicker)
                        .font(.subheadline)
                        .fontWeight(.bold)
                    Text(viewModel.companyName)
                        .font(.title)
                    Text(viewModel.stockPrice)
                        .font(.title)
                }
            }
            .padding(.horizontal, 15)
        }.frame(width: 350, height: 100, alignment: .topLeading)
            .padding(.vertical, 30)
    }
    
    var aboutCompany: some View {
        ZStack(alignment: .center){
            RoundedRectangle(cornerRadius: 20)
                .strokeBorder()
            VStack(alignment: .leading, spacing: 20){
                //List {
                    Group {
                        Text("About")
                            .font(.title2)
                            .bold()
                        Text(viewModel.description)
                            .font(.subheadline)
                    }
                    
                    HStack {
                        Text("CEO")
                        Spacer()
                        Text(viewModel.ceo)
                            .bold()
                            .multilineTextAlignment(.trailing)
                    }
                    
                    HStack {
                        Text("Industry")
                        Spacer()
                        Text(viewModel.industry)
                            .bold()
                            .multilineTextAlignment(.trailing)
                    }
                    
                    HStack {
                        Text("No. of Employees")
                        Spacer()
                        Text(viewModel.employeeNum)
                            .bold()
                            .padding(.trailing)
                    }
                    
                    HStack {
                        Text("Headquarters")
                        Spacer()
                        Text(viewModel.hq)
                            .bold()
                            .multilineTextAlignment(.trailing)
                    }
                //}
            }
            //.frame(width: 360, height: 300, alignment: .center)
            .padding(.horizontal, 30)
            .padding(.vertical, 30)
        }
        .frame(width: 360, height: 320, alignment: .center)
    }
    
    var companyStats: some View {
        ZStack(alignment: .center){
            RoundedRectangle(cornerRadius: 20)
                .strokeBorder()
            VStack(alignment: .leading, spacing: 20){
                //List {
                    Group {
                        Text("Stats")
                            .font(.title2)
                            .bold()
                    }
                    HStack {
                        HStack(spacing: 35) {
                            VStack(alignment: .leading, spacing: 15) {
                                Text("Open")
                                Text("High")
                                Text("Low")
                                Text("52 Wk high")
                                Text("52 Wk low")
                            }
                            
                            VStack(alignment: .leading, spacing: 15) {
                                Text(viewModel.open)
                                    .bold()
                                Text(viewModel.high)
                                    .bold()
                                Text(viewModel.low)
                                    .bold()
                                Text(viewModel.highIn52)
                                    .bold()
                                Text(viewModel.lowIn52)
                                    .bold()
                            }
                        }
                        
                        HStack(spacing: 35) {
                            VStack(alignment: .leading, spacing: 15) {
                                Text("Volume")
                                Text("Avg Vol")
                                Text("Mkt Cap")
                                Text("P/E Ratio")
                                Text("Div/yield")
                            }
                            
                            VStack(alignment: .leading, spacing: 15) {
                                Text(viewModel.volume)
                                    .bold()
                                    .multilineTextAlignment(.trailing)
                                
                                Text(viewModel.averageVol)
                                    .bold()
                                    .multilineTextAlignment(.trailing)
                                Text(viewModel.marketCap)
                                    .bold()
                                    .multilineTextAlignment(.trailing)
                                Text(viewModel.pToERatio)
                                    .bold()
                                    .multilineTextAlignment(.trailing)
                                Text(viewModel.dividend)
                                    .bold()
                                    .multilineTextAlignment(.trailing)
                            }
                            .multilineTextAlignment(.trailing)
                        }
                    }
                //}
            }
            .padding(.horizontal, 30)
            .padding(.vertical, 30)
            //.frame(width: 360, height: 300, alignment: .center)
        }
        .frame(width: 360, height: 320, alignment: .center)
    }
    
}

struct StockView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = SkyrocketViewModel()
        StockView(viewModel: viewModel)
    }
}
