//
//  RLInterestVM.swift
//  RecLoc
//
//  Created by Paril Shah on 5/1/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import SwiftyJSON

class RLInterestVM {
    private var urlString: String
    private var parameters: [String:Any]?
    private var SuccessBlock: (AnyObject) -> Void
    
    init(urlString: String, paramerts: [String:Any]?, block:@escaping (AnyObject)->Void) {
        self.urlString = urlString
        self.parameters = paramerts
        self.SuccessBlock = block
    }
    
    // To fetch categories from server
    public func fetchCategories(){
        let request:RLNetworking = RLNetworking.init()
        request.fetchResponseUsingGet(url: self.urlString, parameters: self.parameters, block:{(response:JSON) -> Void in
            var arry = [AnyObject]()
            for i in response["category"].array!{
                let cat = Categories.init(jsonObject: i)
                arry.append(cat as AnyObject)
            }
            self.SuccessBlock(arry as AnyObject)
        })
    }
    
    public func submitUser(){
        let request:RLNetworking = RLNetworking.init()
        request.fetchResponseUsingPost(url: self.urlString, parameters: self.parameters, block: {(response:JSON) -> Void in
            print(response)
            let dictionary:[String:Any] = response.dictionaryObject!
            self.SuccessBlock(dictionary as AnyObject)
        })
    }

}
