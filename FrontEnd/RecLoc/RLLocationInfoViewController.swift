//
//  RLLocationInfoViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 5/8/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Pulley
import TagListView

class RLLocationInfoViewController: UIViewController {

    public var location:Location?
    public var placeTags:Array<String>?
    public var locationImage:UIImage?
    @IBOutlet weak var placeImageView: UIImageView!
    @IBOutlet weak var placeNameLabel: UILabel!
    @IBOutlet weak var placeDescriptionLabel: UILabel!
    @IBOutlet weak var placeAddressLabel: UILabel!
    @IBOutlet weak var tagListView:TagListView!
    @IBOutlet weak var heightConstraint: NSLayoutConstraint?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        heightConstraint?.constant = UIScreen.main.bounds.size.width
        displayInformation()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func displayInformation() {
        placeNameLabel.text = location?.locationName
        placeDescriptionLabel.text = location?.locationDescription
        placeImageView.image = locationImage
        var variableString:String = ""
        if let value = location?.address?.addressLine1{
            if location?.address?.addressLine1?.characters.count != 0 {
                variableString += value
                variableString += ", "
            }
        }
        if let value = location?.address?.addressLine2 {
            if location?.address?.addressLine2?.characters.count != 0 {
                variableString += value
                variableString += ", "
            }
        }
        if let value = location?.address?.city{
            if location?.address?.city?.characters.count != 0 {
                variableString += value
                variableString += ", "
            }
        }
        if let value = location?.address?.state{
            if location?.address?.state?.characters.count != 0 {
                variableString += value
                variableString += ", "
            }
        }
        if let value = location?.address?.zip{
            if location?.address?.zip?.characters.count != 0 {
                variableString += value
                variableString += ", "
            }
        }
        if let value = location?.address?.country{
            if location?.address?.country?.characters.count != 0 {
                variableString += value
            }
        }
        placeAddressLabel.text = variableString
        
//        if let t = placeTags{
//            for name in t {
//                self.tagListView?.addTag(name)
//            }
//        }
    }

}

extension RLLocationInfoViewController: PulleyDrawerViewControllerDelegate {
    
    func collapsedDrawerHeight() -> CGFloat {
        return 68.0
    }
    
    func partialRevealDrawerHeight() -> CGFloat {
        return 264.0
    }
    
    func supportedDrawerPositions() -> [PulleyPosition] {
        return PulleyPosition.all // You can specify the drawer positions you support. This is the same as: [.open, .partiallyRevealed, .collapsed, .closed]
    }
    
    func drawerPositionDidChange(drawer: PulleyViewController) {
        
        if drawer.drawerPosition != .open {
            
        }
    }
}

