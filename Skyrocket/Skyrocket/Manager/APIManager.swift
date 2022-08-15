//
//  APICaller.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/1/21.
//

import Foundation

/// Finnhub
// Search -> https://finnhub.io/api/v1/search?q=apple&token=c1pi6sqad3id1hoq1j50
// MarketDataResponse -> https://finnhub.io/api/v1/stock/candle?symbol=AAPL&resolution=1&from=1631022248&to=1631627048&token=c1pi6sqad3id1hoq1j50

/// Polygon
// Ticker News -> https://api.polygon.io/v2/reference/news?apiKey=RWSvR5r4zJcy9o8pEWIghjuOzG6jrcch
// Specific Ticker News -> https://api.polygon.io/v2/reference/news?ticker=AAPL&apiKey=RWSvR5r4zJcy9o8pEWIghjuOzG6jrcch
// Stock Financials -> https://api.polygon.io/vX/reference/financials?apiKey=RWSvR5r4zJcy9o8pEWIghjuOzG6jrcch

/// IEX Cloud
// Ticker Details -> https://cloud.iexapis.com/stable/stock/aapl/company?token=pk_ae84422ef18d4675af9baf70acf26594
// Last Price -> https://cloud.iexapis.com/stable/tops/last?symbols=AAPL&token=pk_ae84422ef18d4675af9baf70acf26594
// Stock Quote -> https://cloud.iexapis.com/stable/stock/aapl/quote?token=pk_ae84422ef18d4675af9baf70acf26594

/// Marketstack (Not used at the moment)
// Historical Data -> http://api.marketstack.com/v1/eod?access_key=de90ac7a78061434213051da5c251688&symbols=AAPL
// Tickers -> http://api.marketstack.com/v1/tickers?access_key=de90ac7a78061434213051da5c251688

final class APIManager {
    static let shared = APIManager()
    
    //MARK: - Constants
    private struct K {
        /// - API Keys
        // Polygon.io API Key
        static let polygonApiKey = "RWSvR5r4zJcy9o8pEWIghjuOzG6jrcch"
        // Finnhub API Key
        static let finnhubApiKey = "c1pi6sqad3id1hoq1j50"
        // IEX Cloud API Key
        static let iexCloudApiKey = "pk_ae84422ef18d4675af9baf70acf26594"
        // Marketstack API Key
        static let marketstackApiKey = "de90ac7a78061434213051da5c251688"
        
        /// - Base URLs
        // For Stock Financials, Market Status, and Ticker News
        static let polygonBaseUrl = "https://api.polygon.io/"
        // For Search & Market Data Response
        static let finnhubBaseUrl = "https://finnhub.io/api/v1/"
        // For Ticker Details, Last Price, Market Status, and Stock Quote
        static let iexCloudBaseUrl = "https://cloud.iexapis.com/stable/"
        // For Historical Data and Tickers
        static let marketstackBaseUrl = "http://api.marketstack.com/v1/"
        
        static let day: TimeInterval = 3600*24
    }
    
    private init() {}
    
    //MARK: - Public
    public func search(for symbol: String, completion: @escaping (Result<SearchResponse, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .search, queryParams: ["q": safeQuery,
                                                  "exchange": "US"])
        request(url: url, expecting: SearchResponse.self, completion: completion)
    }
    
    public func details(for symbol: String, completion: @escaping (Result<TickerDetails, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .details, queryParams: ["/": safeQuery])
        request(url: url, expecting: TickerDetails.self, completion: completion)
    }
    
    public func financials(for symbol: String, completion: @escaping (Result<StockFinancials, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .financials, queryParams: ["ticker": safeQuery])
        request(url: url, expecting: StockFinancials.self, completion: completion)
    }
    
    public func last(for symbol: String, completion: @escaping (Result<[LastPrice], Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .last, queryParams: ["symbols": safeQuery])
        request(url: url, expecting: [LastPrice].self, completion: completion)
    }
    
    public func historicalData(for symbol: String, completion: @escaping (Result<HistoricalPriceData, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .historicalData, queryParams: ["symbols": safeQuery])
        request(url: url, expecting: HistoricalPriceData.self, completion: completion)
    }
    
    public func marketData(for symbol: String, numberOfDays: TimeInterval = 7, completion: @escaping (Result<MarketDataResponse, Error>) -> Void) {
        let today = Date().addingTimeInterval(-(K.day))
        let prior = today.addingTimeInterval(-(K.day * numberOfDays))
        request(url: url(for: .marketData, queryParams: ["symbol": symbol,
                                                         "resolution": "1",
                                                         "from": "\(Int(prior.timeIntervalSince1970))",
                                                         "to": "\(Int(today.timeIntervalSince1970))"]),
                expecting: MarketDataResponse.self,
                completion: completion)
    }
    
    public func stockQuote(for symbol: String, completion: @escaping (Result<StockQuote, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .stockQuote, queryParams: ["": safeQuery])
        request(url: url, expecting: StockQuote.self, completion: completion)
    }
    
    public func getNews(completion: @escaping (Result<TickerNews, Error>) -> Void) {
        let url = url(for: .news)
        request(url: url, expecting: TickerNews.self, completion: completion)
    }
    
    public func getNews(for symbol: String, completion: @escaping (Result<TickerNews, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .news, queryParams: ["ticker": safeQuery])
        request(url: url, expecting: TickerNews.self, completion: completion)
    }
    
    public func getNews(for symbol: String, limit: Int, completion: @escaping (Result<TickerNews, Error>) -> Void) {
        guard let safeQuery = symbol.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {return}
        let url = url(for: .news, queryParams: ["ticker": safeQuery,
                                                "limit": String(limit)])
        request(url: url, expecting: TickerNews.self, completion: completion)
    }
    
    //MARK: - Private
    private enum Endpoint: String {
        // Finnhub
        case search
        case marketData = "stock/candle"
        // Polygon
        case financials = "vX/reference/financials"
        case news = "v2/reference/news"
        // IEX Cloud
        case details = "stock"
        case last = "tops/last"
        case stockQuote = "stock/"
        // MarketStack
        case historicalData = "eod"
    }
    
    private enum APIError: Error {
        case noDataReturned
        case invalidUrl
    }
    
    private func url(for endpoint: Endpoint, queryParams: [String: String] = [:]) -> URL? {
        var urlString = ""
        var queryString = ""
        var queryItems = [URLQueryItem]()
        
        // Add any parameters
        for (name, value) in queryParams {
            queryItems.append(.init(name: name, value: value))
        }
        
        switch endpoint {
        case .search, .marketData:
            urlString = K.finnhubBaseUrl + endpoint.rawValue
            queryItems.append(.init(name: "token", value: K.finnhubApiKey))
            queryString = queryItems.map {"\($0.name)=\($0.value ?? "")"}.joined(separator: "&")
                
        case .financials, .news:
            urlString = K.polygonBaseUrl + endpoint.rawValue
            queryItems.append(.init(name: "apiKey", value: K.polygonApiKey))
            queryString = queryItems.map {"\($0.name)=\($0.value ?? "")"}.joined(separator: "&")
            
        case .details, .stockQuote:
            urlString = K.iexCloudBaseUrl + endpoint.rawValue
            queryItems.append(.init(name: "token", value: K.iexCloudApiKey))
            queryString = queryItems.map {
                if $0.name == "/" {
                    urlString.append(contentsOf: "\($0.name)\($0.value ?? "")/company")
                    return ""
                } else if $0.name == "" {
                    urlString.append(contentsOf: "\($0.name)\($0.value ?? "")/quote")
                    return ""
                }else {
                    return "\($0.name)=\($0.value ?? "")"
                }
            }.joined(separator: "")
        
        case .last:
            urlString = K.iexCloudBaseUrl + endpoint.rawValue
            queryItems.append(.init(name: "token", value: K.iexCloudApiKey))
            queryString = queryItems.map {"\($0.name)=\($0.value ?? "")"}.joined(separator: "&")
            
        case .historicalData:
            urlString = K.marketstackBaseUrl + endpoint.rawValue
            queryItems.append(.init(name: "access_key", value: K.marketstackApiKey))
            queryString = queryItems.map {"\($0.name)=\($0.value ?? "")"}.joined(separator: "&")
        }
        
        urlString += "?" + queryString
        print("\n\(urlString)\n")
        
        return URL(string: urlString)
    }
    
    private func request<T: Codable>(
        url: URL?,
        expecting: T.Type,
        completion: @escaping (Result<T, Error>) -> Void) {
        guard let url = url else {
            // Custom Errors
            completion(.failure(APIError.invalidUrl))
            return
        }
        
        let task = URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data, error == nil else {
                if let error = error {
                    completion(.failure(error))
                } else {
                    completion(.failure(APIError.noDataReturned))
                }
                return
            }
            
            do {
                let result = try JSONDecoder().decode(expecting, from:data)
                completion(.success(result))
            } catch {
                completion(.failure(error))
            }
        }
        task.resume()
    }
}
