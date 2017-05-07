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
            var arry = [AnyObject]()
            for i in response.array!{
                let tempLocation = i["locationDetails"] as JSON
                let location = Location.init(jsonObject: tempLocation as JSON)
                arry.append(location as AnyObject)
            }
            self.SuccessBlock(arry as AnyObject)
        })
    }
    
    public func fetchUserLocations(){
        let request:RLNetworking = RLNetworking.init()
        let url = urlForFetchUserLocation()
        request.fetchResponseUsingGet(url: url, parameters: nil, block: {(response:JSON) -> Void in
            var arry = [AnyObject]()
            for i in response.array!{
                let tempLocation = i["locationDetails"] as JSON
                let location = Location.init(jsonObject: tempLocation as JSON)
                arry.append(location as AnyObject)
            }
            self.SuccessBlock(arry as AnyObject)
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
    
    private func urlForFetchUserLocation()->String{
        let user = UserDefaults.standard.object(forKey: "User") as! String
        let url = "\(self.urlString)/\(user)"
        return url
    }

}
