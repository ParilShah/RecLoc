//
//  RLPlacesVM.swift
//  RecLoc
//
//  Created by Paril Shah on 5/4/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import SwiftyJSON

class RLPlacesVM {
    private var urlString: String
    private var parameters: [String:Any]?
    private var SuccessBlock: (AnyObject) -> Void
    
    init(urlString: String, paramerts: [String:Any]?, block:@escaping (AnyObject)->Void) {
        self.urlString = urlString
        self.parameters = paramerts
        self.SuccessBlock = block
    }
    
    // To fetch locations from server on basis of User's interest.  
    public func fetchLocationsByCategoriesIds(){
        self.parameters = parametersForFetchLocationsByCategories()
        let request:RLNetworking = RLNetworking.init()
        request.fetchResponseUsingPost(url: self.urlString, parameters: self.parameters, block:{(response:JSON) -> Void in
            print(response)
//            var arry = [AnyObject]()
//            for i in response["category"].array!{
//                let cat = Categories.init(jsonObject: i)
//                arry.append(cat as AnyObject)
//            }
//            self.SuccessBlock(arry as AnyObject)
        })
    }
    
    public func fetchUserLocations(){
        self.parameters = parametersForFetchUserLocation()
        let request:RLNetworking = RLNetworking.init()
        request.fetchResponseUsingPost(url: self.urlString, parameters: self.parameters, block: {(response:JSON) -> Void in
            print(response)
//            let dictionary:[String:Any] = response.dictionaryObject!
//            self.SuccessBlock(dictionary as AnyObject)
        })
    }
    
    private func retrieveSavedCategories()->AnyObject{
        let categories = UserDefaults.standard.object(forKey: "Categories") as AnyObject
        return categories as AnyObject
    }
    
    private func parametersForFetchLocationsByCategories()->[String:AnyObject]{
        let categories = retrieveSavedCategories() 
        let parameters: [String:AnyObject] = ["category": categories as AnyObject]
        return parameters
    }
    
    private func parametersForFetchUserLocation()->[String:String]{
        let user = UserDefaults.standard.object(forKey: "User") as! String
        let parameters: [String:String] = ["deviceId": user]
        return parameters
    }

}
